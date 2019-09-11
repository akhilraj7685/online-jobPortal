package com.nt.service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nt.dto.CompanyDto;
import com.nt.dto.CountryDto;
import com.nt.dto.DistricDto;
import com.nt.dto.JobDto;
import com.nt.dto.LocationDto;
import com.nt.dto.SkillDto;
import com.nt.dto.StateDto;
import com.nt.entity.Company;
import com.nt.entity.Country;
import com.nt.entity.Job;
import com.nt.entity.Job.JobType;
import com.nt.entity.Location;
import com.nt.entity.Skill;
import com.nt.entity.Users;
import com.nt.repo.CountryRepo;
import com.nt.repo.JobRepo;
import com.nt.repo.LocationRepo;
import com.nt.repo.UsersRepo;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepo jobRepo; 
	@Autowired
	private UsersRepo usersRepo;
	@Autowired
	private CountryRepo countryRepo;
	@Autowired
	private LocationRepo locRepo;
	
	
	
	
/**
 * create new job
 */
	@Override
	@Transactional(transactionManager="transactionManager")
	public JobDto createJob(JobDto jobDto)throws Exception {
    Job job=null,job2=null;
    JobDto jobDto2=null;
   
    Users user=null;
    Company cmp=null;
    System.out.println("service----------1");
    user=usersRepo.getOne(jobDto.getUser().getUserId());
    List<Location> locList=new ArrayList<Location>();
    List<Skill> skillList=new ArrayList<Skill>();
    cmp=new Company();
    System.out.println("service----------2");
    if(jobDto!=null) {
    	job=new Job();
    	System.out.println("service----------3");
    	
    	//copy all locations from dto to entity
    	jobDto.getLocations().forEach(locDto->{
    		Location loc=new Location();
    		BeanUtils.copyProperties(locDto, loc);
    		locList.add(loc);
    	});
    	System.out.println("service----------4");
    	//copy all skills from dto to entity
    	jobDto.getSkills().forEach(skillDto->{
    		Skill skill=new Skill();
    		BeanUtils.copyProperties(skillDto, skill);
    		skillList.add(skill);
    	});
    	System.out.println("service----------5");
    	//copy users from userDto to entity
    	//BeanUtils.copyProperties(jobDto.getUsers(), users);
    	//copy company from dto to entity
    	BeanUtils.copyProperties(jobDto.getCompany(), cmp);
    	
    	System.out.println("service----------6");
    	
    	BeanUtils.copyProperties(jobDto, job);
    	job.setCompany(cmp);
    	job.setLocations(locList);
    	job.setSkills(skillList);
    	job.setUser(user);
    	
    	
    	System.out.println("service----------7");
    	//
    	//save entity
    	job2=jobRepo.save(job);
    	//job2=jobRepo.saveAndFlush(job);
    	System.out.println("service----------8");
    	System.out.println("job---"+job2.toString());
    }
      
    if(job2==null)
    	throw new IllegalArgumentException("entity can't be saved. Try again");
	//convert entity to dto
    System.out.println("job--2---"+job2.toString());
    jobDto2=new JobDto();
    BeanUtils.copyProperties(job2, jobDto2);
    System.out.println("jobDto2-----"+jobDto2.toString());
    return jobDto2;
	}

	
	
	@Override
	public List<CountryDto> getAllCountries() {
		List<Country> cntryList=null;
		List<CountryDto> cntryDtoList=null;
		List<StateDto> stateDtoList=null;
		List<DistricDto> districDtoList=null;
		
			cntryList=countryRepo.findAll();
			
			cntryList.forEach(cntry->{
				CountryDto cntryDto=new CountryDto();
				BeanUtils.copyProperties(cntry, cntryDto);
				
				
			});//country
			
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	/**
	 * get all jobs
	 */
	@Override
	public  Map<String,Object> getAllJobs()throws Exception {
		int pageNo=0;
		int pageSize=25;
		
		Map<String, Object> result=getJobsByPage(pageNo, pageSize);
		return result;
	}
	
	
	
	
	
	
	

	
	
	/**
	 * get all jobs
	 */
	@Override
	public Map<String,Object> getJobsByPage(int pageNo,int pageSize)throws Exception {
		List<Job> jobList=null;
		
		List<JobDto> jobDtoList=new ArrayList<>();
		Page<Job> page=Page.empty();
		
		if(pageNo<0 || pageNo>100)
			pageNo=0;
		if(pageSize<0 || pageSize>250)
			pageSize=100;
		
		PageRequest pageRequest=PageRequest.of(pageNo, pageSize, Sort.by("postingDate").descending());
		
		
		Map<String, Object> result=new HashMap<>();
		
		
		
		
		page=jobRepo.findAll(pageRequest);
		
		
		
		
		//convert jobList into dtoList
		page.forEach(job->{
			List<LocationDto> locList=new ArrayList<>();
			List<SkillDto> skillList=new ArrayList<>();
			JobDto jobDto=new JobDto();
			 //copy company entity to company dto
			CompanyDto cmpDto=new CompanyDto();
			BeanUtils.copyProperties(job.getCompany(),cmpDto);
			
			//copy locations from entity to dto 
			//List<LocationDto> locList=new ArrayList<>(); 
			System.out.println("----------------"+job.getLocations().size()); 
			job.getLocations().forEach(loc->{ 
				 System.out.println("------------location::--------------------------"+loc.getCity());
				 LocationDto locDto=new LocationDto();
				 BeanUtils.copyProperties(loc, locDto);
				 locList.add(locDto);
			 });
			 
			 //copy skills from entity to dto
			
			 job.getSkills().forEach(skill->{
				 System.out.println("--------------skill-----------------------"+skill.getSkillName());
				 SkillDto skillDto=new SkillDto();
				 BeanUtils.copyProperties(skill,skillDto);
				 skillList.add(skillDto);
			 });
			 
			 
			 //copy simple values of entity to dto
			 BeanUtils.copyProperties(job,jobDto);
			 //copy objects type values from entity to dto
			 jobDto.setCompany(cmpDto);  
			 jobDto.setLocations(locList);
			 jobDto.setSkills(skillList);
			 
			 //add the prepared jobDto to list collection
			 jobDtoList.add(jobDto);
		});
		
		
		System.out.println("11111111111111111111111111------"+jobDtoList.size());
		//set jobDtoList and pagable object to map because in controller pagable will may be used
		result.put("jobDtoList",jobDtoList);
		result.put("page",page);
		
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * get job by jobId
	 */
@Override
	public JobDto getJobById(long jobId) {
	JobDto jobDto=new JobDto();
	Job job=null;
	
	//convert jobId to long type according to the id of entity
	
	//get job entity from repository
	job=jobRepo.getOne(jobId);
	
	//convert entity into dto
		List<LocationDto> locList=new ArrayList<>();
		List<SkillDto> skillList=new ArrayList<>();
		
		 //copy company entity to company dto
		CompanyDto cmpDto=new CompanyDto();
		BeanUtils.copyProperties(job.getCompany(),cmpDto);
		
		//copy locations from entity to dto 
		//List<LocationDto> locList=new ArrayList<>(); 
		System.out.println("----------------"+job.getLocations().size()); 
		job.getLocations().forEach(loc->{ 
			 System.out.println("------------location::--------------------------"+loc.getCity());
			 LocationDto locDto=new LocationDto();
			 BeanUtils.copyProperties(loc, locDto);
			 locList.add(locDto);
		 });
		 
		 //copy skills from entity to dto
		
		 job.getSkills().forEach(skill->{
			 System.out.println("--------------skill-----------------------"+skill.getSkillName());
			 SkillDto skillDto=new SkillDto();
			 BeanUtils.copyProperties(skill,skillDto);
			 skillList.add(skillDto);
		 });
		 
		 
		 //copy simple values of entity to dto
		 BeanUtils.copyProperties(job,jobDto);
		 //copy objects type values from entity to dto
		 jobDto.setCompany(cmpDto);  
		 jobDto.setLocations(locList);
		 jobDto.setSkills(skillList);
		 
		
		return jobDto;
	}
	
	















/**
 * get state-wise cities of india
 * this method works only if country name is only one
 */
@Override
public Set<Location> getStateCity(){
	HashSet<Location> hs=new HashSet<Location>();
	
	Comparator<Location> cmp=new Comparator<Location>() {
		int diff=0;
		@Override
		public int compare(Location o1, Location o2) {
			      // if((diff=o1.getCountry().compareToIgnoreCase(o2.getCountry()))!=0)
			    	//   return diff;
			       //else {
			    	   if((diff=o1.getState().compareToIgnoreCase(o2.getState()))!=0)
			    		   return diff;
			    	   else {
			    		   if((diff=o1.getCity().compareToIgnoreCase(o2.getCity()))!=0)
			    			   return diff;
			    		   else {
			    			   diff=(int) (o1.getPin()-o2.getPin());
			    				   return diff;
			    			  
			    		   }
			    	   }
			      //}//else
			    	   
			       
		}
	};
	
	TreeSet<Location>  ts=new TreeSet<Location>(cmp);
	locRepo.findAll().forEach(loc->{
		System.out.println(ts.add(loc));
		System.out.println("loc--"+loc);
	});

	
	
	return ts;
	
}//method end



@Override
public Map<String, Object> jobsByCity(String state, String city) throws Exception {
	Map<String,Object> result=new HashMap<String, Object>();
	List<JobDto> jobDtoList=null;
	int pageNo=0;
	int pageSize=2;
	Page<Job> page=Page.empty();
	PageRequest pageRequest=PageRequest.of(pageNo, pageSize, Sort.by("postingDate").descending());
	page=jobRepo.findAllByLocations(state, city,pageRequest); 
	jobDtoList=copyJobToDto(page);
	
	//set jobDtoList and pagable object to map because in controller pagable will may be used
			result.put("jobDtoList",jobDtoList);
			result.put("page",page);
	
	return result;
}














/**
 * get all jobs of given JobType
 */
   @Override
    public Map<String, Object> jobsByJobType(String jobType) throws Exception {
	List<JobDto> jobDtoList=null;
	Map<String,Object> result=new HashMap<String, Object>();
	int pageSize=25;
	int pageNo=0;
	Page<Job> page=Page.empty();
	PageRequest pageRequest=PageRequest.of(pageNo, pageSize, Sort.by("postingDate").descending());
	   
	//assure the argument String is a jobType or not	 
	  JobType jobType0=JobType.valueOf(jobType);
	  
	  
	page = jobRepo.findAllByJobType(jobType0,pageRequest);
	
	//copy page values to dtoList
	jobDtoList=copyJobToDto(page);

	
	//set jobDtoList and pagable object to map because in controller pagable will may be used
	result.put("jobDtoList",jobDtoList);
	result.put("page",page);

    return result;
}

   
   
   
   
 
   
   @Override
public List<JobType> getJobTypes() {
	   
	   
	   List<JobType> jobType=null; 
	   
	   		
		System.out.println("hm-------");
		jobType=jobRepo.findAllJobType();
		Comparator<JobType>cmp=new  Comparator<Job.JobType>() {
			
			@Override
			public int compare(JobType o1, JobType o2) {
				return o1.name().compareTo(o2.name());
				
			}
		};
		jobType.sort(cmp);
	return jobType;
}
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   @Override
public Map<String, Object> jobsBySkillCity(String city,String... skills) throws Exception {
  System.out.println("JobServiceImpl.jobsBySkillCity()");
	   PageRequest pageRequest=PageRequest.of(0,25,Sort.by("postingDate").descending());
	Page<Job> page=Page.empty();  
  page=jobRepo.findAllBySkillsAndCityIn("saharsa",pageRequest,"tata","bata");
  page.forEach(job->{
	  System.out.println("job-----"+job);
  });
  System.out.println("jb size---"+page.getNumberOfElements());
  
	   
	   
	return null;
}
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   


/**
 * utitlity method to convert all jobs from page to jobDtos
 * @param page
 * @return List<JobDto>
 */
private List<JobDto> copyJobToDto(Page<Job> page){
	
	List<JobDto> jobDtoList=new ArrayList<JobDto>();
   
	//convert jobList into dtoList
	page.forEach(job->{
		List<LocationDto> locList=new ArrayList<>();
		List<SkillDto> skillList=new ArrayList<>();
		JobDto jobDto=new JobDto();
		 //copy company entity to company dto
		CompanyDto cmpDto=new CompanyDto();
		BeanUtils.copyProperties(job.getCompany(),cmpDto);
		
		//copy locations from entity to dto 
		//List<LocationDto> locList=new ArrayList<>(); 
		System.out.println("----------------"+job.getLocations().size()); 
		job.getLocations().forEach(loc->{ 
			 System.out.println("------------location::--------------------------"+loc.getCity());
			 LocationDto locDto=new LocationDto();
			 BeanUtils.copyProperties(loc, locDto);
			 locList.add(locDto);
		 });
		 
		 //copy skills from entity to dto
		
		 job.getSkills().forEach(skill->{
			 System.out.println("--------------skill-----------------------"+skill.getSkillName());
			 SkillDto skillDto=new SkillDto();
			 BeanUtils.copyProperties(skill,skillDto);
			 skillList.add(skillDto);
		 });
		 
		 
		 //copy simple values of entity to dto
		 BeanUtils.copyProperties(job,jobDto);
		 //copy objects type values from entity to dto
		 jobDto.setCompany(cmpDto);  
		 jobDto.setLocations(locList);
		 jobDto.setSkills(skillList);
		 
		 //add the prepared jobDto to list collection
		 jobDtoList.add(jobDto);
	});
	
	return jobDtoList;
	
}


}//class
