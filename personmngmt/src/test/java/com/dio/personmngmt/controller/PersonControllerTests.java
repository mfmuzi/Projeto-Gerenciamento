

package com.dio.personmngmt.controller;



        import com.dio.personmngmt.dto.request.PersonDTO;
        import com.dio.personmngmt.dto.response.MessageResponseDTO;
        import com.dio.personmngmt.exception.PersonNotFoundException;
        import com.dio.personmngmt.service.PersonService;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoExtension;
        import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
        import org.springframework.http.MediaType;
        import org.springframework.test.web.servlet.MockMvc;
        import org.springframework.test.web.servlet.setup.MockMvcBuilders;
        import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

        import java.util.Collections;
        import java.util.List;

        import static com.dio.personmngmt.utils.PersonUtils.asJsonString;
        import static com.dio.personmngmt.utils.PersonUtils.createFakeDTO;
        import static org.hamcrest.core.Is.is;
        import static org.mockito.Mockito.when;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTests {


    private static final String PEOPLE_API_URL_PATH = "/api/v1/people";

    private MockMvc mockMvc;

    @Mock
    private PersonService personService;

    @BeforeEach
    void setUp() {
        PersonController personController = new PersonController(personService);
        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

//Teste adicionar usuário.
    @Test
    void testPostPerson() throws Exception {
        PersonDTO expectedPersonDTO = createFakeDTO();
        MessageResponseDTO expectedResponseMessage = createMessageResponse("Created person with id", 1L);

        when(personService.createPerson(expectedPersonDTO)).thenReturn(expectedResponseMessage);

        mockMvc.perform(post(PEOPLE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedPersonDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(expectedResponseMessage.getMessage())));
    }

//Teste retornar usuário com id válido.
    @Test
    void testGetValidId() throws Exception {
        long expectedValidId = 1L;
        PersonDTO expectedPersonDTO = createFakeDTO();
        expectedPersonDTO.setId(expectedValidId);

        when(personService.findById(expectedValidId)).thenReturn(expectedPersonDTO);

        mockMvc.perform(get(PEOPLE_API_URL_PATH + "/" + expectedValidId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Maria")))
                .andExpect(jsonPath("$.lastName", is("Ribeiro")));
    }

//Teste retornar usuário com id inválido.
    @Test
    void testGetInvalidId() throws Exception {
        long expectedValidId = 1L;
        PersonDTO expectedPersonDTO = createFakeDTO();
        expectedPersonDTO.setId(expectedValidId);

        when(personService.findById(expectedValidId)).thenThrow(PersonNotFoundException.class);

        mockMvc.perform(get(PEOPLE_API_URL_PATH + "/" + expectedValidId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

//Teste retornar lista de usuários.
    @Test
    void testGetListPerson() throws Exception {
        long expectedValidId = 1L;
        PersonDTO expectedPersonDTO = createFakeDTO();
        expectedPersonDTO.setId(expectedValidId);
        List<PersonDTO> expectedPeopleDTOList = Collections.singletonList(expectedPersonDTO);

        when(personService.listAll()).thenReturn(expectedPeopleDTOList);

        mockMvc.perform(get(PEOPLE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Maria")))
                .andExpect(jsonPath("$[0].lastName", is("Ribeiro")));
    }

//Teste atualizar usuário.
    @Test
    void testPutPerson() throws Exception {
        long expectedValidId = 1L;
        PersonDTO expectedPersonDTO = createFakeDTO();
        MessageResponseDTO expectedResponseMessage = createMessageResponse("Updated person with id ", 1L);

        when(personService.updateById(expectedValidId, expectedPersonDTO)).thenReturn(expectedResponseMessage);

        mockMvc.perform(put(PEOPLE_API_URL_PATH + "/" + expectedValidId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedPersonDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(expectedResponseMessage.getMessage())));
    }

//Teste deletar usuário.
    @Test
    void testDeletePerson() throws Exception {
        long expectedValidId = 1L;

        mockMvc.perform(delete(PEOPLE_API_URL_PATH + "/" + expectedValidId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private MessageResponseDTO createMessageResponse(String message, long id) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }
}

