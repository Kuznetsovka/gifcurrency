package ru.kuznetsovka.gifcurrency.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootTest
@EnableWebMvc
//@RunWith(MockitoJUnitRunner.class)
class GifControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//    @MockBean
//    MyService myService;
//
//    private final String CURRENCY = "RUB";
//    private RateDto rate;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//
//        rate = new RateDto(CURRENCY,63.499993);
//    }

//    @SneakyThrows
//    @Test
//    void getJsonRateTodaySecond() {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/api/v1/check/" + CURRENCY + "/today")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$.rate").value(CURRENCY));
//    }

//    @SneakyThrows
//    @Test
//    void getJsonRateYesterday() {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/api/v1/check/" + CURRENCY + "/yesterday")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$.rate").value(CURRENCY));
//    }
}