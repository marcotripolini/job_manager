package it.improvity.beans;

import java.util.Date;

public class JobInstance {
	private Long id;
	private Long job_id;
	private Long job_agent;
	private Long job_sequence;
	private String job_name;
	private String job_command;
	private String job_workingdir;
	private String job_file;
	private String job_file_2;
	private Date job_creation_date;
	private Date job_execution_date;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the job_id
	 */
	public Long getJob_id() {
		return job_id;
	}

	/**
	 * @param job_id the job_id to set
	 */
	public void setJob_id(Long job_id) {
		this.job_id = job_id;
	}

	/**
	 * @return the job_agent
	 */
	public Long getJob_agent() {
		return job_agent;
	}

	/**
	 * @param job_agent the job_agent to set
	 */
	public void setJob_agent(Long job_agent) {
		this.job_agent = job_agent;
	}

	/**
	 * @return the job_sequence
	 */
	public Long getJob_sequence() {
		return job_sequence;
	}

	/**
	 * @param job_sequence the job_sequence to set
	 */
	public void setJob_sequence(Long job_sequence) {
		this.job_sequence = job_sequence;
	}

	/**
	 * @return the job_name
	 */
	public String getJob_name() {
		return job_name;
	}

	/**
	 * @param job_name the job_name to set
	 */
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	/**
	 * @return the job_command
	 */
	public String getJob_command() {
		return job_command;
	}

	/**
	 * @param job_command the job_command to set
	 */
	public void setJob_command(String job_command) {
		this.job_command = job_command;
	}

	/**
	 * @return the job_workingdir
	 */
	public String getJob_workingdir() {
		return job_workingdir;
	}

	/**
	 * @param job_workingdir the job_workingdir to set
	 */
	public void setJob_workingdir(String job_workingdir) {
		this.job_workingdir = job_workingdir;
	}

	/**
	 * @return the job_file
	 */
	public String getJob_file() {
		return job_file;
	}

	/**
	 * @param job_file the job_file to set
	 */
	public void setJob_file(String job_file) {
		this.job_file = job_file;
	}

	/**
	 * @return the job_file_2
	 */
	public String getJob_file_2() {
		return job_file_2;
	}

	/**
	 * @param job_file_2 the job_file_2 to set
	 */
	public void setJob_file_2(String job_file_2) {
		this.job_file_2 = job_file_2;
	}

	/**
	 * @return the job_creation_date
	 */
	public Date getJob_creation_date() {
		return job_creation_date;
	}

	/**
	 * @param job_creation_date the job_creation_date to set
	 */
	public void setJob_creation_date(Date job_creation_date) {
		this.job_creation_date = job_creation_date;
	}

	/**
	 * @return the job_execution_date
	 */
	public Date getJob_execution_date() {
		return job_execution_date;
	}

	/**
	 * @param job_execution_date the job_execution_date to set
	 */
	public void setJob_execution_date(Date job_execution_date) {
		this.job_execution_date = job_execution_date;
	}

}
