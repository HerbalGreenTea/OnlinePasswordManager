package com.project.passmanager.main.network.pages;

import com.project.passmanager.main.database.core.InMemoryCacheSecretSpace;
import com.project.passmanager.main.domain.models.SecretSpace;
import com.project.passmanager.main.network.services.SecretService;
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
    private final SecretService secretService;

    public static String redirectOnSelectedSpaceSecretsPage(String secretSpaceId) {
        return String.format("redirect:/spaceSecret/%s", secretSpaceId);
    }

    public static String refreshPage() {
        return "redirect:/spaceSecret";
    }

    public String openSecretsListPage(@NonNull String spaceId, Model model) {
        model.addAttribute("spaces", spaceSecretsService.getSecretSpaces());
        model.addAttribute("secrets", secretService.getSecretsBySecretSpaceId(spaceId));
        return PAGE_NAME;
    }

    public String openEmptySecretsListPage(Model model) {
        return openSecretsListPage("", model);
    }

    public String saveSecretSpace(SecretSpace secretSpace) {
        if (!secretSpace.getName().isBlank()) {
            spaceSecretsService.saveSecretSpace(secretSpace);
        }
        if (spaceSecretsService.getSecretSpaces().size() == 1) {
            SELECTED_SECRET_SPACE_ID = secretSpace.getId();
        }
        return refreshPage();
    }

    public String deleteSecretSpace(String secretSpaceId) {
        spaceSecretsService.deleteSecretSpace(secretSpaceId);

        var spaces = spaceSecretsService.getSecretSpaces();
        if (spaces.isEmpty()) {
            SELECTED_SECRET_SPACE_ID = "";
        } else {
            SELECTED_SECRET_SPACE_ID = spaces.get(0).getId();
        }

        return refreshPage();
    }
}
