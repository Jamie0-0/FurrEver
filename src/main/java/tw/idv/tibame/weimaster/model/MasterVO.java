package tw.idv.tibame.weimaster.model;

import java.time.LocalDateTime;

public class MasterVO {

    private Integer m_id;
    private String m_name;
    private String m_pwd;
    private String m_gui;
	private String m_bank_name;
	private String m_bank_id;
	private String m_address;
	private LocalDateTime m_reg;
	private String m_man_id;
	private String m_man_name;
	private String m_email;
	private String m_phone;
	private Integer m_status;
	private Integer gm_id;
	private LocalDateTime gm_date;

	public Integer getM_id() {
		return m_id;
	}

	public String getM_name() {
		return m_name;
	}

	public String getM_pwd() {
		return m_pwd;
	}

	public String getM_gui() {
		return m_gui;
	}

	public String getM_bank_name() {
		return m_bank_name;
	}

	public String getM_bank_id() {
		return m_bank_id;
	}

	public String getM_address() {
		return m_address;
	}

	public LocalDateTime getM_reg() {
		return m_reg;
	}

	public String getM_man_id() {
		return m_man_id;
	}

	public String getM_man_name() {
		return m_man_name;
	}

	public String getM_email() {
		return m_email;
	}
	
	public String getM_phone() {
		return m_phone;
	}
	
	public Integer getM_status() {
		return m_status;
	}
	
	public Integer getGm_id() {
		return gm_id;
	}
	
	public LocalDateTime getGm_date() {
		return gm_date;
	}

	private MasterVO() {
		super();
	}

	private MasterVO(MasterVO.Builder builder) {
		m_id = builder.m_id;
		m_name = builder.m_name;
		m_pwd = builder.m_pwd;
		m_gui = builder.m_gui;
		m_bank_name = builder.m_bank_name;
		m_bank_id = builder.m_bank_id;
		m_address = builder.m_address;
		m_reg = builder.m_reg;
		m_man_id = builder.m_man_id;
		m_man_name = builder.m_man_name;
		m_email = builder.m_email;
		m_phone = builder.m_phone;
		m_status = builder.m_status;
		gm_id = builder.gm_id;
		gm_date = builder.gm_date;
	}
	
	public static class Builder {
	    private Integer m_id = 0;
	    private String m_name = "";
	    private String m_pwd = "";
	    private String m_gui = "";
		private String m_bank_name = "";
		private String m_bank_id = "";
		private String m_address = "";
		private LocalDateTime m_reg = null;
		private String m_man_id = "";
		private String m_man_name = "";
		private String m_email = "";
		private String m_phone = "";
		private Integer m_status = 0;
		private Integer gm_id = 0;
		private LocalDateTime gm_date = null;

		public MasterVO.Builder setM_id(Integer m_id) {
			this.m_id = m_id;
			return this;
		}
		
		public MasterVO.Builder setM_name(String m_name) {
			this.m_name = m_name;
			return this;
		}

		public MasterVO.Builder setM_pwd(String m_pwd) {
			this.m_pwd = m_pwd;
			return this;
		}
		
		public MasterVO.Builder setM_gui(String m_gui) {
			this.m_gui = m_gui;
			return this;
		}
		
		public MasterVO.Builder setM_bank_name(String m_bank_name) {
			this.m_bank_name = m_bank_name;
			return this;
		}
		
		public MasterVO.Builder setM_bank_id(String m_bank_id) {
			this.m_bank_id = m_bank_id;
			return this;
		}
		
		public MasterVO.Builder setM_address(String m_address) {
			this.m_address = m_address;
			return this;
		}
		
		public MasterVO.Builder setM_reg(LocalDateTime m_reg) {
			this.m_reg = m_reg;
			return this;
		}
		
		public MasterVO.Builder setM_man_id(String m_man_id) {
			this.m_man_id = m_man_id;
			return this;
		}
		
		public MasterVO.Builder setM_man_name(String m_man_name) {
			this.m_man_name = m_man_name;
			return this;
		}
		
		public MasterVO.Builder setM_email(String m_email) {
			this.m_email = m_email;
			return this;
		}
		
		public MasterVO.Builder setM_phone(String m_phone) {
			this.m_phone = m_phone;
			return this;
		}
		
		public MasterVO.Builder setM_status(Integer m_status) {
			this.m_status = m_status;
			return this;
		}
		
		public MasterVO.Builder setGm_id(Integer gm_id) {
			this.gm_id = gm_id;
			return this;
		}
		
		public MasterVO.Builder setGm_date(LocalDateTime gm_date) {
			this.gm_date = gm_date;
			return this;
		}

		public MasterVO build() {
			return new MasterVO(this);
		}
	}
}
