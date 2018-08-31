package com.ctrip.framework.apollo.portal.repository;

import com.ctrip.framework.apollo.common.entity.App;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;


public interface AppRepository extends PagingAndSortingRepository<App, Long> {

  App findByAppId(String appId);

  List<App> findByOwnerName(String ownerName, Pageable page);

  List<App> findByAppIdIn(Set<String> appIds);

}
