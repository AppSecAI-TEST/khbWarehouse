package com.yeepay.g3.core.activity.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityUserRaffleticket;
import com.yeepay.g3.core.activity.service.ActivityUserRaffleTicketService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityUserRaffleticketDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUsercouponDTO;
import com.yeepay.g3.facade.activity.facade.ActivityUserRaffleTicketFacade;
@Service
public class ActivityUserRaffleTicketFacadeImpl implements
		ActivityUserRaffleTicketFacade {
	
	@Autowired
	private ActivityUserRaffleTicketService ActivityUserRaffleTicketServiceImpl;

	@Override
	public ActivityUserRaffleticketDTO selectUserRaffleTicketById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addActivityUserRaffleTicket(
			ActivityUsercouponDTO activityUserRaffleTicketDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ActivityUserRaffleticketDTO> selectListByParams(
			ActivityUserRaffleticketDTO activityUserRaffleTicketDto) {
		
		List<ActivityUserRaffleticket> userRaffleticketList = new ArrayList<ActivityUserRaffleticket>();
		if (null != activityUserRaffleTicketDto
				&& null != activityUserRaffleTicketDto.getMemberNo()
				&& null != activityUserRaffleTicketDto.getStatus()) {
			userRaffleticketList = ActivityUserRaffleTicketServiceImpl.selectListByParams(activityUserRaffleTicketDto);

		}
		List<ActivityUserRaffleticketDTO> userRaffleticketDtoList = EntityDTOConvert
				.toTragetList(userRaffleticketList,
						ActivityUserRaffleticketDTO.class);
		return userRaffleticketDtoList;
	}

	/* (non-Javadoc)
	 * @see com.yeepay.g3.facade.activity.facade.ActivityUserRaffleTicketFacade#addActivityUserRaffleTicketList(java.lang.String, java.lang.Integer, java.lang.Long)
	 */
	@Override
	public void addActivityUserRaffleTicketList(String memberNoList,
	Integer num, Long id,Long version,Integer grantCount,String actionCode,String activityCode) throws Exception  {
		ActivityUserRaffleTicketServiceImpl.addActivityUserRaffleTicketList(memberNoList, num, id, version, grantCount, actionCode, activityCode);
		
	}
	
}
