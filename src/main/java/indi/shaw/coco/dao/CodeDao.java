/**
 * @Package: indi.shaw.coco.dao
 * @author: shaw
 * @date: 2019年4月9日 上午11:27:21
 */
package indi.shaw.coco.dao;

import java.io.Serializable;

import indi.shaw.coco.model.Code;

/**
 * @author shaw
 *
 */
public interface CodeDao<PK extends Serializable> extends BaseDao<Code, PK> {

}
