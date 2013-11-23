package com.atlas.plugin.cf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.ofbiz.core.entity.GenericValue;

import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.config.ConstantsManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.customfields.impl.CalculatedCFType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.web.action.func.EventType;
import com.oracle.jrockit.jfr.EventToken;



public class NewCustomField extends CalculatedCFType {

	public NewCustomField() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getSingularObjectFromString(String arg0)
			throws FieldValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStringFromSingularObject(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValueFromIssue(CustomField arg0, Issue arg1) {
		// TODO Auto-generated method stub

		int counter = 0; 
		
		String configuredStatusesList = "Sub-tasks statuses: ";

		if (!arg1.getSubTaskObjects().isEmpty()) {
			//Checks whether Issue has Sub-Task or not
			

			ArrayList<MutableIssue> StandardIssue = new ArrayList<MutableIssue>();
			StandardIssue = (ArrayList<MutableIssue>) arg1.getSubTaskObjects();
			//Keeps the list of Sub-tasks Issue has
			
			ArrayList<String> list = new ArrayList<String>();
			//Keeps the list of each Sub-Task's statuses
			
			counter = arg1.getSubTasks().size();
			//Keeps the number of Sub-Tasks Issue has


			for (int i = 0; i < counter; i++) {

				list.add(StandardIssue.get(i).getStatusObject().getName());

			}

			Map<String, Integer> occurrencies = new HashMap<String, Integer>();
			//Created to make a search over arraylist
			for (String word : list) {
				occurrencies
						.put(word,
								occurrencies.containsKey(word) ? occurrencies
										.get(word) + 1 : 1);
			}
			
			for (Entry<String, Integer> entry : occurrencies.entrySet()) {
				configuredStatusesList = configuredStatusesList
						+ entry.getKey() + ": " + entry.getValue() + " <br>";
			}
			/*Checks how many Open, Closed and ... statuses Sub-Tasks has by searching over an array of list counting occurrencies
			No hardcoding, custom field displays new values statuses*/

			String htmlOutput = "<p> Standard Issue has "
					+ arg1.getSubTasks().size() + " sub issues <br> "
					+ configuredStatusesList + "</p>";
			return htmlOutput;
			//Returns htmlOutput for Issues having Sub-tasks
		}

		else {
			String htmlOutputTwo = "<p> Standard Issue has "
					+ arg1.getSubTasks().size() + " sub issues </p>";
			return htmlOutputTwo;
			//Returns htmlOutput for Issues not having Sub-tasks

		}
	}

}
