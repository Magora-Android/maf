package com.magorasystems.mafmodules.common.application;

import android.content.Context;

import com.magorasystems.mafmodules.common.dagger.HasComponent;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public interface ComponentApplication<COMPONENT> extends HasComponent<COMPONENT> {

    Context getContext();
}
