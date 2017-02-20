package com.scut.rsyncWeb.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
//返回上一级目录
public class LastCatalogAction {

	public String execute() throws Exception {
		//用户session
		Map sess= ActionContext.getContext().getSession();
		chooseCatalogAction chooseCatalog = new chooseCatalogAction();
		String path = (String) sess.get("path");
		if(path == null || path == ""){return "false";}
		if(path == "/"){return "false";}
		
		int a =path.lastIndexOf("/");
		if(a==0 || a==-1){path = "";}else{
		path = path.substring(0, a);
		}
		sess.remove("path");
		sess.put("path", path);
		chooseCatalog.setFilename("");
		chooseCatalog.execute();
		System.out.println(path);
		System.out.println(a);
		return "success";
	}
}
