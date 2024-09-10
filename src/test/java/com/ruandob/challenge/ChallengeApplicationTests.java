package com.ruandob.challenge;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ChallengeApplication.class)
class ChallengeApplicationTests {

    @Test
    @DisplayName("Deve carregar o contexto da apliação com sucesso!")
    void shouldLoadContext(ApplicationContext context) {
        ChallengeApplication.main(new String[]{});
        Assertions.assertThat(context).isNotNull();
    }
}
