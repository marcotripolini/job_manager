package it.improvity.sbeans;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class uCookie {
	private String jsessionid;
	
	public String execute() {
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.removeAttribute("ID");
			session.invalidate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String getJsessionid() {
		return jsessionid;
	}

	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}
}
