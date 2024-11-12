package com.infosys.demo.dao;

import java.util.List;

import com.infosys.demo.bean.ShowTime;

public interface ShowTimeDao {

	public void save(ShowTime showTime);
	public Integer generateShowTimeId();
	public ShowTime findById(Integer id);
	public List<ShowTime> findAll();
	public void deleteShowTimeById(Integer id);
	public void updateShowTimeById(Integer id, String newName, Double newRoyalPrice, Double newPremierPrice);
	public List<Integer> getAllIds();
}
