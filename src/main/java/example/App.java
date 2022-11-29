package example;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.*;
import javax.xml.bind.JAXBException;
import java.io.FileReader;
import java.io.IOException;

@WebServlet("/test")
public class App extends HttpServlet {

    @Inject
    JavaBean javaBean;
    Convertor convertor = new Convertor();
    private final String fileName = "XML.xml";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UserEntity user = new UserEntity();
        FileWorker fileWorker = new FileWorker();
        fileWorker.createFile(fileName);
        try {
            String res =convertor.fromEntityToXML(javaBean.findUser(16));
            fileWorker.save(fileName,res);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            FileReader fileReader = new FileReader(fileName);
            UserEntity data = convertor.fromXmlToEntity(fileReader);
            javaBean.saveUser(data);
        } catch (JAXBException | SystemException | NotSupportedException | HeuristicRollbackException |
                 HeuristicMixedException | RollbackException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }

}