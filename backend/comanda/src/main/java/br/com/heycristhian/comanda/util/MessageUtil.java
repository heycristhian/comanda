package br.com.heycristhian.comanda.util;

import br.com.heycristhian.comanda.controller.dto.request.ClientRequest;
import br.com.heycristhian.comanda.controller.dto.response.ClientResponse;
import br.com.heycristhian.comanda.domain.Client;
import br.com.heycristhian.comanda.domain.User;

public abstract class MessageUtil {

    private MessageUtil() {
    }

    public static final String STARTING_SAVE_OBJECT_DATABASE = "Starting to save {} in database";
    public static final String STARTING_UPDATE_OBJECT_DATABASE = "Starting to update {} in database";
    public static final String STARTING_FIND_OBJECT = "Starting the {} search in the database";
    public static final String STARTING_DELETE_OBJECT = "Starting to delete {} in database";

    public static final String SEARCHING_OBJECT_DATABASE = "Searching {} in database";
    public static final String SAVING_OBJECT_DATABASE = "Saving {} in database";
    public static final String UPDATING_OBJECT_DATABASE = "Updating {} in database";
    public static final String DELETING_OBJECT_DATABASE = "Deleting {} in database";

    public static final String MAPPING_TO = "Mapping {} to {}";

    public static final String HTTP_RESPONSE = "Returning request response";

    public static final String CLIENT_NAME_ENTITY = Client.class.getSimpleName();
    public static final String CLIENT_REQUEST_NAME_ENTITY = ClientRequest.class.getSimpleName();
    public static final String CLIENT_RESPONSE_NAME_ENTITY = ClientResponse.class.getSimpleName();

    public static final String USER_NAME_ENTITY = User.class.getSimpleName();

}