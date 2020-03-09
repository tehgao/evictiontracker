package org.dsacleveland.evictiontracker.service.mapper.legacy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;
import org.dsacleveland.evictiontracker.model.evictiondata.legacy.LegacyCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LegacyCaseMapperTest {

    private LegacyCaseMapper classUnderTest;

    @BeforeEach
    public void setup() {
        this.classUnderTest = LegacyCaseMapper.INSTANCE;
    }

    @Test
    public void testMapToEntity() throws JsonProcessingException {
        LegacyCase legacyCase = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .readValue("{\n" +
                        "    \"id\": 94,\n" +
                        "    \"plaintiffs\": [\n" +
                        "      {\n" +
                        "        \"name\": \"DOE, JOHN\",\n" +
                        "        \"address\": {\n" +
                        "          \"id\": 208,\n" +
                        "          \"street_address\": \"23518 MAIN ST\",\n" +
                        "          \"street_address_2\": \"\",\n" +
                        "          \"city\": \"WESTLAKE\",\n" +
                        "          \"state\": \"OH\",\n" +
                        "          \"zip\": \"44145\"\n" +
                        "        },\n" +
                        "        \"attorney_set\": [\n" +
                        "          {\n" +
                        "            \"name\": \"DOE, ESQ. JANE\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"defendants\": [\n" +
                        "      {\n" +
                        "        \"name\": \"ALICE WONDERLAND\",\n" +
                        "        \"address\": {\n" +
                        "          \"id\": 209,\n" +
                        "          \"street_address\": \"3691 WEST 138TH ST DOWN\",\n" +
                        "          \"street_address_2\": \"\",\n" +
                        "          \"city\": \"CLEVELAND\",\n" +
                        "          \"state\": \"OH\",\n" +
                        "          \"zip\": \"44111\"\n" +
                        "        },\n" +
                        "        \"attorney_set\": []\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"additional_parties\": [],\n" +
                        "    \"event_set\": [\n" +
                        "      {\n" +
                        "        \"id\": 173,\n" +
                        "        \"event_type\": \"SC\",\n" +
                        "        \"is_pro_se\": false,\n" +
                        "        \"date_time\": \"2020-02-21T18:30:00Z\",\n" +
                        "        \"assoc_case\": 94\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"id\": 174,\n" +
                        "        \"event_type\": \"FC\",\n" +
                        "        \"is_pro_se\": false,\n" +
                        "        \"date_time\": \"2020-01-24T14:00:00Z\",\n" +
                        "        \"assoc_case\": 94\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"case_number\": \"2020 CVG 000140\",\n" +
                        "    \"file_date\": \"2020-01-03\"\n" +
                        "  }", LegacyCase.class);

        CaseDto actual = this.classUnderTest.toEntity(legacyCase);
        System.out.println(actual);
    }
}