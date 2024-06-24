/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.system.mapper;

import com.cmir.system.domain.SysNotice;
import java.util.List;

/**
 * 通知公告表 数据层
 *
 * @author galudisu
 */
public interface SysNoticeMapper {
  /**
   * 查询公告信息
   *
   * @param noticeId 公告ID
   * @return 公告信息
   */
  public SysNotice selectNoticeById(Long noticeId);

  /**
   * 查询公告列表
   *
   * @param notice 公告信息
   * @return 公告集合
   */
  public List<SysNotice> selectNoticeList(SysNotice notice);

  /**
   * 新增公告
   *
   * @param notice 公告信息
   * @return 结果
   */
  public int insertNotice(SysNotice notice);

  /**
   * 修改公告
   *
   * @param notice 公告信息
   * @return 结果
   */
  public int updateNotice(SysNotice notice);

  /**
   * 批量删除公告
   *
   * @param noticeId 公告ID
   * @return 结果
   */
  public int deleteNoticeById(Long noticeId);

  /**
   * 批量删除公告信息
   *
   * @param noticeIds 需要删除的公告ID
   * @return 结果
   */
  public int deleteNoticeByIds(Long[] noticeIds);
}
