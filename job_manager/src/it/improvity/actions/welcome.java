package it.improvity.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Query;
import org.hibernate.Session;
import com.opensymphony.xwork2.ActionSupport;

import it.improvity.beans.Job;
import it.improvity.beans.JobInstance;
import it.improvity.beans.Risoluzione;
import it.improvity.hibernate.HibernateUtil;
import it.improvity.hibernate.HibernateUtil2;
import it.improvity.interfaces.Constants;

public class welcome extends ActionSupport implements Constants, SessionAware {
	private static final long serialVersionUID = 7578894634627494161L;
	private String action;
	private SessionMap<String, Object> sessionMap;

	@SuppressWarnings("unchecked")
	public String execute() {
		try {

			// HttpSession session = ServletActionContext.getRequest().getSession();
			Session sx = HibernateUtil.getSessionFactory().openSession(); 		// database configuratore
			Session sx2 = HibernateUtil2.getSessionFactory().openSession();		// database job_manager

			Query q = sx.createQuery("from Risoluzione");
			List<Risoluzione> risoluzioni = (List<Risoluzione>) q.list();
			Iterator<Risoluzione> it = risoluzioni.iterator();

			while (it.hasNext()) {
				Risoluzione risoluzione = (Risoluzione) it.next();
				System.out.println(risoluzione.getCodice());
			}


			Query r = sx.createQuery("from Job");
			List<Job> jobs = (List<Job>) r.list();
			Iterator<Job> it1 = jobs.iterator();
			while (it1.hasNext()) {
				Job job = (Job) it1.next();
				System.out.println(job.getMappatura());
			}

			Query s = sx2.createQuery("from JobInstance");
			List<JobInstance> jobsInst = (List<JobInstance>) s.list();
			Iterator<JobInstance> it2 = jobsInst.iterator();
			while (it2.hasNext()) {
				JobInstance j = (JobInstance) it2.next();
				System.out.println(j.getJob_name());
			}

			action = ServletActionContext.getContext().getName();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		sessionMap = (SessionMap) arg0;
	}
}
