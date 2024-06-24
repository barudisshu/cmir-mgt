/*
 * COPYRIGHT Cmir 2024
 *
 * The copyright to the computer program(s) herein is the property of
 * Cmir Inc. The programs may be used and/or copied only with written
 * permission from Cmir Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */

package com.cmir.system.service.impl;

import com.cmir.system.domain.SysNotice;
import com.cmir.system.mapper.SysNoticeMapper;
import com.cmir.system.service.ISysNoticeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公告 服务层实现
 *
 * @author galudisu
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService {
  @Autowired private SysNoticeMapper noticeMapper;

  /**
   * 查询公告信息
   *
   * @param noticeId 公告ID
   * @return 公告信息
   */
  @Override
  public SysNotice selectNoticeById(Long noticeId) {
    return noticeMapper.selectNoticeById(noticeId);
  }

  /**
   * 查询公告列表
   *
   * @param notice 公告信息
   * @return 公告集合
   */
  @Override
  public List<SysNotice> selectNoticeList(SysNotice notice) {
    return noticeMapper.selectNoticeList(notice);
  }

  /**
   * 新增公告
   *
   * @param notice 公告信息
   * @return 结果
   */
  @Override
  public int insertNotice(SysNotice notice) {
    return noticeMapper.insertNotice(notice);
  }

  /**
   * 修改公告
   *
   * @param notice 公告信息
   * @return 结果
   */
  @Override
  public int updateNotice(SysNotice notice) {
    return noticeMapper.updateNotice(notice);
  }

  /**
   * 删除公告对象
   *
   * @param noticeId 公告ID
   * @return 结果
   */
  @Override
  public int deleteNoticeById(Long noticeId) {
    return noticeMapper.deleteNoticeById(noticeId);
  }

  /**
   * 批量删除公告信息
   *
   * @param noticeIds 需要删除的公告ID
   * @return 结果
   */
  @Override
  public int deleteNoticeByIds(Long[] noticeIds) {
    return noticeMapper.deleteNoticeByIds(noticeIds);
  }
}
