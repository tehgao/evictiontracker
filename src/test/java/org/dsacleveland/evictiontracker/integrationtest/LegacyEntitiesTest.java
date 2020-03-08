package org.dsacleveland.evictiontracker.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.dsacleveland.evictiontracker.model.evictiondata.legacy.LegacyCase;
import org.dsacleveland.evictiontracker.service.evictiondata.CaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LegacyEntitiesTest {
    @Autowired
    private CaseService caseService;

    @Test
    void testLoad() throws IOException {
        String file = new String(Files.readAllBytes(Paths.get("src/main/resources/json/evictions_sample.json")));
        LegacyCase[] legacyCases = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .readValue(file, LegacyCase[].class);

        this.caseService.importFromLegacy(Arrays.asList(legacyCases));

        assertNotNull(this.caseService.readAll());
        assertEquals(legacyCases.length, this.caseService.readAll().size());
    }
}
