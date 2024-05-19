package com.osiris.api.controllers.internal;

import com.osiris.api.constants.AppUrls;
import com.osiris.api.services.LocalCacheManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By francislagueu on 5/15/24
 */
@Slf4j
@RestController
@RequestMapping(CacheInternalApiController.BASE_URL)
@RequiredArgsConstructor
public class CacheInternalApiController {
    public static final String BASE_URL = AppUrls.INTERNAL + "/caches";

    private final LocalCacheManagerService localCacheManagerService;

    @DeleteMapping
    public ResponseEntity<Void> clearCaches() {
        log.info("[request] clearing all local caches");
        this.localCacheManagerService.evictAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cache-name}")
    public ResponseEntity<Void> clearCaches(@PathVariable("cache-name") final String cacheName) {
        log.info("[request] clearing local cache '{}'", cacheName);
        this.localCacheManagerService.evictByName(cacheName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/kubernetes")
    public ResponseEntity<Void> evictAllCacheFromKubernetesPods() {
        log.info("[request] evicting all cache from kubernetes pods");
        this.localCacheManagerService.evictCacheInAllKubernetesInstances();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/kubernetes/{cache-name}")
    public ResponseEntity<Void> evictAllCacheFromKubernetesPods(
            @PathVariable("cache-name") final String cacheName) {
        log.info("[request] evicting cache {} from kubernetes pods", cacheName);
        this.localCacheManagerService.evictCacheInAllKubernetesInstances(cacheName);
        return ResponseEntity.ok().build();
    }
}
