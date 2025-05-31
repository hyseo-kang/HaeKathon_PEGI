package service;
import java.util.*;

import domain.GradualStatus;
import dto.*;

public class GraduationCal {
	
	public void setGraduationStatus(List<Subject> subjectList, GradualCredit gradualCredit, GradualStatus status)
	{
		if (status == null) {
	        throw new IllegalArgumentException("정보를 받을 status를 불러오지 못했습니다.");
	    }
		status.setCurCredit(getTotalCredit(subjectList));
		status.setSemPerCredit(getSemesterCredit(subjectList));
		// status.setRetaken(countRetakenSubject(subjectList));
	    status.setGCredit(gradualCredit.getTotalCredit());
	    status.setRemainCredit(gradualCredit.getTotalCredit() - status.getCurCredit());
	    double rPercent = (double) status.getRemainCredit() / status.getGCredit();
	    status.setRemainPercent(Math.round(rPercent * 100) / 100.0);
	}
	
	// 재수강 삭제 로직
	/*
	 * public List<SubjectInfo> getModifyList(List<SubjectInfo> originList) {
		Map<String, SubjectInfo> modifyObj = new HashMap<String, SubjectInfo>();
		
		for (SubjectInfo subject : originList) {
			String code = subject.getCode();
			
			if ( !modifyObj.containsKey(code) )
				modifyObj.put(code, subject);
			else {
				if (subject.isLaterSem(modifyObj.get(code)))
					modifyObj.put(code, subject);
			}
		}
		
		return new ArrayList<>(modifyObj.values());
	}
	 * */
	
	// 재수강 한 과목 수 계산
	/*
	 * public int countRetakenSubject(List<SubjectInfo> originList) {
		List<SubjectInfo> modifyList = getModifyList(originList);
		return originList.size() - modifyList.size();
	}
	 * */
	
	
	/* Map< 학기, 학기별 이수학점> 반환 */
	public Map<String, Double> getSemesterCredit(List<Subject> subjectList) {
		Map<String, Double> semCredit = new HashMap<String, Double>();
		
		for (Subject subject : subjectList) {
			String sem = subject.semester;
			double credit = subject.credit;
			
			if ( !semCredit.containsKey(sem) )
				semCredit.put(sem, credit);
			else {
				double curCredit = semCredit.get(sem);
				semCredit.put(sem, curCredit + credit);
			}
		}
		
		return semCredit;
	}
	
	public int getTotalCredit(List<Subject> subjectList) {
		int totalCredit = 0;
		
		for(Subject subject : subjectList)
			totalCredit += subject.credit;
		
		return totalCredit;
	}
	
	
}
