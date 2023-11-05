package com.project.passmanager.main.network.pages;

import com.project.passmanager.main.database.core.InMemoryCacheSecretSpace;
import com.project.passmanager.main.network.services.SpaceSecretsService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
@RequiredArgsConstructor
public class SpaceSecretsPage {
    private static final String PAGE_NAME = "secretsListPage";
    public static String SELECTED_SECRET_SPACE_ID = InMemoryCacheSecretSpace.getIdDefaultSecretSpace();

    private final SpaceSecretsService spaceSecretsService;

    public static String redirectOnSelectedSpaceSecretsPage(String secretSpaceId) {
        return String.format("redirect:/spaceSecret/%s", secretSpaceId);
    }

    public String openSecretsListPage(@NonNull String spaceId, Model model) {
        model.addAttribute("spaces", spaceSecretsService.getSecretSpaces());
        model.addAttribute("secrets", spaceSecretsService.getSecretsBySpaceSecretsId(spaceId));
        return PAGE_NAME;
    }

    public String openEmptySecretsListPage(Model model) {
        return openSecretsListPage("", model);
    }
}
