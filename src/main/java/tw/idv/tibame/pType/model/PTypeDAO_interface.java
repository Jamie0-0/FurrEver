package tw.idv.tibame.pType.model;

import java.util.List;

public interface PTypeDAO_interface {
	public void insert(PTypeVO pTypeVO);

	public PTypeVO findByPrimaryKey(Integer pt_id);

	public List<PTypeVO> getAll();
}
