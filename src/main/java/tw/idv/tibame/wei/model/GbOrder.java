package tw.idv.tibame.wei.model;

import java.time.LocalDateTime;

public class GbOrder {
	Integer gb_id;
	Integer people;
	Integer gb_c_max;
	Integer gb_s_price;
	LocalDateTime gb_time_end;
	String gb_s;
	public Integer getGb_id() {
		return gb_id;
	}
	public void setGb_id(Integer gb_id) {
		this.gb_id = gb_id;
	}
	public Integer getPeople() {
		return people;
	}
	public void setPeople(Integer people) {
		this.people = people;
	}
	public Integer getGb_c_max() {
		return gb_c_max;
	}
	public void setGb_c_max(Integer gb_c_max) {
		this.gb_c_max = gb_c_max;
	}
	public Integer getGb_s_price() {
		return gb_s_price;
	}
	public void setGb_s_price(Integer gb_s_price) {
		this.gb_s_price = gb_s_price;
	}
	public LocalDateTime getGb_time_end() {
		return gb_time_end;
	}
	public void setGb_time_end(LocalDateTime gb_time_end) {
		this.gb_time_end = gb_time_end;
	}
	public String getGb_s() {
		return gb_s;
	}
	public void setGb_s(String gb_s) {
		this.gb_s = gb_s;
	}
}
