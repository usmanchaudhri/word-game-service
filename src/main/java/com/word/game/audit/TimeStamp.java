package com.word.game.audit;

import java.util.Date;

public interface TimeStamp {
	public Date getCreatedOn();
	public void setCreatedOn(Date createdOn);
	public Date getUpdatedOn();
	public void setUpdatedOn(Date updatedOn);
}
