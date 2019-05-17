/**
 * @Package: indi.shaw.coco.dao.impl
 * @author: shaw
 * @date: 2019年4月8日 下午11:25:41
 */
package indi.shaw.coco.dao.impl;

import org.springframework.stereotype.Repository;

import indi.shaw.coco.dao.CodeDao;
import indi.shaw.coco.model.Code;

/**
 * @author shaw
 *
 */
@Repository("codeDao")
public class CodeDaoImpl extends BaseDaoImpl<Code> implements CodeDao<Long> {
}
