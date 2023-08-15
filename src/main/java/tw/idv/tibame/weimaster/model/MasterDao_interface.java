package tw.idv.tibame.weimaster.model;

public interface MasterDao_interface {
	public void update(MasterVO masterVO);

	public MasterVO findByPrimaryKey(Integer m_id);

}
