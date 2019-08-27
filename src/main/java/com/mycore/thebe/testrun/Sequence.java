package com.mycore.thebe.testrun;

public class Sequence {

	/*
	 * Sequence example to create and do sequence incremental
	 * STEP:
	 * 1. Create RULE TEMPLATE
	 * ---${counter}/${option01}/${option02}/${month}/${year}
	 * 2. Create SEQUENCE SCRIPT
	 * ---SELECT NEXTVAL('|sequence_name|')"
	 * 3. Execute query to create sequence
	 * ---call method createSquence(...) in class SequenceCreator.java
	 * 4. Apply on data
	 * --- replacing ${....} by data and for ${counter} replace with
	 * --- method doSequence(...) in class SequenceCreator.java 
	 * 5. New month reset sequence
	 */
	
}
