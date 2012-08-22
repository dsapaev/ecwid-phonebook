package com.ecwidtest.phonebook.server;

import com.ecwidtest.phonebook.client.PhoneBookService;
import com.ecwidtest.phonebook.common.bean.HumanDTO;
import com.ecwidtest.phonebook.common.bean.PhoneNumberDTO;
import com.ecwidtest.phonebook.server.bean.Human;
import com.ecwidtest.phonebook.server.bean.PhoneNumber;
import com.ecwidtest.phonebook.server.dao.HumanDao;
import com.ecwidtest.phonebook.server.dao.PhoneNumberDao;
import com.google.gwt.view.client.Range;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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

    @Override
    public void deleteNumbers(List<PhoneNumberDTO> toDelete) {
        for(PhoneNumberDTO dto: toDelete){
            phoneNumberDao.delete(dto.getId());
        }
    }

    @Override
    public Long addNumber(PhoneNumberDTO number) {
        HumanDTO owner = number.getOwner();

        Human human = new Human();

        human.setFirstName(owner.getFirstName());
        human.setLastName(owner.getLastName());

        if(owner.getId()==null){
            owner.setId(humanDao.add(human));
        }

        PhoneNumber phoneNumber = new PhoneNumber();

        phoneNumber.setPhoneNumber(number.getPhoneNum());
        phoneNumber.setOwner(human);
        return phoneNumberDao.add(phoneNumber);
    }

    @Override
    public void editNumber(PhoneNumberDTO number) {
        HumanDTO owner = number.getOwner();

        Human human = new Human();

        human.setId(owner.getId());
        human.setFirstName(owner.getFirstName());
        human.setLastName(owner.getLastName());

        PhoneNumber phoneNumber = new PhoneNumber();

        phoneNumber.setId(number.getId());
        phoneNumber.setPhoneNumber(number.getPhoneNum());
        phoneNumber.setOwner(human);

        phoneNumberDao.add(phoneNumber);
    }

	@Override
	public List<PhoneNumberDTO> loadData(Range range) {
		List<PhoneNumberDTO> result = new ArrayList<PhoneNumberDTO>();
		List<PhoneNumber> numberList = phoneNumberDao.loadList();
		PhoneNumberDTO dto;
		Human human;
		HumanDTO humanDTO;
		
		for(PhoneNumber number: numberList){
			dto = new PhoneNumberDTO();
			dto.setId(number.getId());
			dto.setPhoneNum(number.getPhoneNumber());
			
			human = number.getOwner();
			humanDTO = new HumanDTO();
			humanDTO.setId(human.getId());
			humanDTO.setFirstName(human.getFirstName());
			humanDTO.setLastName(human.getLastName());
			
			dto.setOwner(humanDTO);
			result.add(dto);
		}
		
		return result;
	}
}
