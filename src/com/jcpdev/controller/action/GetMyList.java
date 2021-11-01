package com.jcpdev.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jcpdev.dao.MemberDao;
import com.jcpdev.dao.ProductDao;
import com.jcpdev.dto.Member;
import com.jcpdev.dto.NavCnt;
import com.jcpdev.dto.Product;

public class GetMyList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		ProductDao dao = ProductDao.getInstance();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("user_id");
		MemberDao mdao = MemberDao.getInstance();

		Member user = mdao.getInfo(id);
		NavCnt cnt = mdao.navCntUpdate(id);
		List<Product> list = dao.getMyList(user);

		request.setAttribute("list", list);
		request.setAttribute("cnt", cnt);
		ActionForward foward = new ActionForward();
		foward.isRedirect = false;
		foward.url = "/view/my_product.jsp";
		return foward;
	}

}