package kk.lanluyu.projecthelper.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author zzh
 * @date 2024/05/04
 */
@RequiredArgsConstructor
@Getter
public enum YesOrNoEnum {

    YES(1),
    NO(0);

    private final int value;
}
