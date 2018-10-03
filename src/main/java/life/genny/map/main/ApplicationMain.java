package life.genny.map.main;

import life.genny.map.config.ApplicationServer;
import life.genny.map.task.QuestionMapTask;
import life.genny.qwanda.Question;

public class ApplicationMain {

  public static void main(String[] args) {
    ApplicationServer server = new ApplicationServer();
    server.startServer();
    QuestionMapTask qt = new QuestionMapTask();
    Question fetchQuestionById = qt.fetchQuestionById(2L);
    System.out.println(fetchQuestionById);
  }

}


