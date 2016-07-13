package com.magorasystems.mafmodules.view.impl;

import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.magorasystems.mafmodules.common.mvp.view.impl.AbstractLceView;
import com.magorasystems.mafmodules.model.UserProfile;
import com.magorasystems.widgets.utils.ColorGenerator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;

/**
 * Developed by Magora Team (magora-systems.com). 2016.
 *
 * @author Valentin S.Bolkonsky
 */
public class UserProfileLceViewImpl extends AbstractLceView<UserProfile> implements UserProfileLceView {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileLceViewImpl.class);

    private final WeakReference<SimpleDraweeView> avatarView;
    private final WeakReference<CollapsingToolbarLayout> toolbar;

    public UserProfileLceViewImpl(View progressView, View contentView, SimpleDraweeView avatar, CollapsingToolbarLayout toolbar) {
        super(progressView, contentView);
        this.avatarView = new WeakReference<>(avatar);
        this.toolbar = new WeakReference<>(toolbar);
    }

    @Override
    public void setModel(UserProfile model) {
        LOGGER.debug("model {} ", model);
        setHeaderData(model);
    }

    @Override
    public void detachView() {
        super.detachView();
        avatarView.clear();
        toolbar.clear();
    }


    @Override
    public void showError(Throwable e) {
        LOGGER.error("something wrong ", e);
    }

    private void setHeaderData(@NonNull UserProfile profile) {
        final SimpleDraweeView draweeAvatar = avatarView.get();
        if (draweeAvatar != null) {
            draweeAvatar.getHierarchy().setPlaceholderImage(new ColorDrawable(ColorGenerator.MATERIAL.getRandomColor()));
            draweeAvatar.setImageURI(profile.getAvatar());
        }
        final CollapsingToolbarLayout toolbar = this.toolbar.get();
        if (toolbar != null) {
            if (StringUtils.isNotBlank(profile.getName())) {
                toolbar.setTitle(profile.getName());
            } else {
                toolbar.setTitle("");
            }
        }
    }
}
