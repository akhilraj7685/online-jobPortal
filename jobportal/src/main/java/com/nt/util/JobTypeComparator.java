package com.nt.util;

import java.util.Comparator;

import com.nt.entity.Job.JobType;

public class JobTypeComparator implements Comparator<JobType> {

	@Override
	public int compare(JobType o1, JobType o2) {
		return o1.name().compareTo(o2.name());
		
	}
	



}
