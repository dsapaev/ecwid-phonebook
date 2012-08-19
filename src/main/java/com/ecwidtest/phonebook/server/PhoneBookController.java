package com.ecwidtest.phonebook.server;

import com.ecwidtest.phonebook.client.PhoneBookService;
import com.ecwidtest.phonebook.server.bean.PhoneNumber;
import com.ecwidtest.phonebook.server.dao.HumanDao;
import com.ecwidtest.phonebook.server.dao.PhoneNumberDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author d.sapaev
 */
@Controller
@RequestMapping("/phoneBook")
public class PhoneBookController implements PhoneBookService {
  private Logger logger = LoggerFactory.getLogger(getClass());
  @Autowired
  private HumanDao humanDao;

  @Autowired
  private PhoneNumberDao phoneNumberDao;

  @RequestMapping("index.htm")
  public ModelAndView showForm() {
    ModelAndView mv = new ModelAndView("phoneBook");
    return mv;
  }

  @RequestMapping("phoneList.rpc")
  public String getMessage(String msg) {
    String answer;
    try {
      List<PhoneNumber> testBean = phoneNumberDao.loadList();
      answer = "Hibernate is working" + testBean.size();
    } catch (Exception ex) {
      answer = "Hibernate is not working...";
      logger.error(ex.toString(), ex);
    }
    return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\" " + answer;
  }
}
