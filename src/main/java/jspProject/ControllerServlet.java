package jspProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(urlPatterns = "*.do", initParams = {@WebInitParam(name = "initParam" , value = "/WEB-INF/commandHandler.properties")})
public class ControllerServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   //<커맨드, 핸들러클래스> 매핑 정보 저장
   
   private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
   
   //서블릿을 생성하고 초기화할 때 제일 먼저 실행되는 init()메소드를 이용
   public void init(ServletConfig config) throws ServletException{
      String configFile = config.getInitParameter("initParam");
      
      Properties prop = new Properties();
      String configFilePath = config.getServletContext().getRealPath(configFile);
      
      try(FileInputStream fis = new FileInputStream(configFilePath)){
         prop.load(fis);
      } catch(IOException e) {
         throw new ServletException(e);
      }
      
      //속성 파일을 하나씩 읽어서 키(호출패턴)와 실행 명령 핸들러명을 맵에 저장
      
      Iterator<Object> keyIter = prop.keySet().iterator();
      
      while(keyIter.hasNext()) {
         String command = (String)keyIter.next();
         String handlerClassName = prop.getProperty(command);
         
         //String 타입의 핸들러객체 이름을 실제 객체로 변환
         try {
            Class<?> handlerClass = Class.forName(handlerClassName);
            CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
            
            commandHandlerMap.put(command, handlerInstance);
         }catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new ServletException(e);
         }
      }
   }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   try {
		   process(request, response);
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
      
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
    	  process(request, response);
      } catch (Exception e) {
    	  e.printStackTrace();
      }
	    
   }

   private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      String command = request.getRequestURI();
      
      
      //contextPath()= /week11
      command = command.substring(request.getContextPath().length()+1);
      //위 명령문 실행 결과 command = hello.do 저장
      
      //command를 키로해서 맵에서 핸들러 객체를 가져온다.
      CommandHandler handler = commandHandlerMap.get(command);
      
      //Object result = null;
      
      String viewPage = null;
	try {
		viewPage = handler.process(request, response);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      //Servlet 컨트롤러에서 뷰 페이지로 넘기는 방법
      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/" + viewPage + ".jsp");
      rd.forward(request, response);
   }

}


