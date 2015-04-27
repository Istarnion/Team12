/*******************************************************************************
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * MonthPicker.java Team 12, 27 Apr 2015
 *******************************************************************************/
package no.hist.aitel.team12.gui;

import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractSpinnerModel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;

import no.hist.aitel.team12.util.Text;

public final class MonthPicker extends JSpinner {

	private static final long serialVersionUID = -6039555310601549467L;

	static final String[] MONTHS = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "des"};

	private String state = null;;
	
	private Date currDate = null;
	
	private boolean trigger = false;
	
	public MonthPicker(String startValue) {
		super();
		
		String[] parts = startValue.split(" ");
		state = Text.getString(parts[0])+" "+parts[1];
		
		MonthSpinnerModel model = new MonthSpinnerModel(startValue);
		super.setModel(model);
		
		MonthSpinnerEditor editor = new MonthSpinnerEditor(this);
		super.setEditor(editor);
	}
	
	public MonthPicker(Calendar startDate) {
		this(MONTHS[startDate.get(Calendar.MONTH)]+" "+startDate.get(Calendar.YEAR));
	}
	
	public MonthPicker() {
		this(Calendar.getInstance());
	}
	
	public void setDate(Object val) {
		if(val != null) {
			trigger = true;
			if(val instanceof String) {
				super.setValue(val);
			}
			else if(val instanceof Date) {
				Calendar cal = Calendar.getInstance();
				cal.setTime((Date)val);
				setDate(cal);
			}
			else if(val instanceof Calendar) {
				Calendar cal = (Calendar)val;
				String newValue = MONTHS[cal.get(Calendar.MONTH)]+" "+cal.get(Calendar.YEAR);
				super.setValue(newValue);
			}
			else {
				throw new IllegalArgumentException(
								"Input must be either String, Date or Calendar! Input recieved: "
								+val.getClass().getSimpleName());
			}
		}
		else {
			throw new NullPointerException();
		}
	}
	
	private class MonthSpinnerEditor extends DefaultEditor {
		
		private static final long serialVersionUID = 153141297311257854L;

		public MonthSpinnerEditor(JSpinner spinner) {
			super(spinner);			
		}
		
		@Override
		public void stateChanged(ChangeEvent ce) {
			super.getTextField().setText(state);
		}
	}
	
	private class MonthSpinnerModel extends AbstractSpinnerModel {

		private static final long serialVersionUID = -4747562218376225200L;

		private int currOffset = 0;

		private int startYear, startMonth;

		public MonthSpinnerModel(String startValue) {
			super();
			String[] parts = startValue.split(" ");
			startMonth = indexOf(MONTHS, parts[0]);
			startYear = Integer.parseInt(parts[1]);
			
			updateDate(startMonth, startYear);
		}

		@Override
		public Object getNextValue() {
			currOffset++;
			return calcValues();
		}

		@Override
		public Object getPreviousValue() {
			currOffset--;
			return calcValues();
		}

		@Override
		public Object getValue() {
			return state;
		}
		
		@Override
		public void setValue(Object value) {
			if(trigger) {
				trigger = false;
				String val = (String)value;
				System.out.println(val);
				String[] parts = val.split(" ");
				startMonth = indexOf(MONTHS, parts[0]);
				startYear = Integer.parseInt(parts[1]);
				
				updateDate(startMonth, startYear);
			}
		}
		
		private Object calcValues() {
			int monthIndex = ((((startMonth+currOffset) % 12) + 12) % 12);
			String month = MONTHS[monthIndex];

			int year = startYear + Math.floorDiv((currOffset+startMonth), 12);
			
			updateDate(monthIndex, year);
			
			return Text.getString(month)+" "+year;
		}
		
		private void updateDate(int month, int year) {
			state = Text.getString(MONTHS[month])+" "+year;
			
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.set(year, month, 1);
			
			currDate = cal.getTime();
			
			fireStateChanged();
		}
	}

	public Date getSelectedDate() {
		return currDate;
	}
	
	static int indexOf(Object[] array, Object item) {
		for(int i=0; i<array.length; i++) {
			if(array[i].equals(item)) return i;
		}
		return -1;
	}
}
