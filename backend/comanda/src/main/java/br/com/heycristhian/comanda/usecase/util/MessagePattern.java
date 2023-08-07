package br.com.heycristhian.comanda.usecase.util;

import br.com.heycristhian.comanda.domain.model.Client;
import br.com.heycristhian.comanda.infrastructure.http.dto.request.UserRequest;
import br.com.heycristhian.comanda.infrastructure.http.dto.response.UserResponse;
import br.com.heycristhian.comanda.infrastructure.database.schema.ClientSchema;
import br.com.heycristhian.comanda.infrastructure.database.schema.UserSchema;

public abstract class MessagePattern {

    private MessagePattern() {
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

    public static final String VALIDATING_PASSWORD = "Validating password";

    public static final String CLIENT_NAME_MODEL = Client.class.getSimpleName();
    public static final String CLIENT_REQUEST_NAME_MODEL = Client.class.getSimpleName();
    public static final String CLIENT_RESPONSE_NAME_ENTITY = Client.class.getSimpleName();

    public static final String CLIENT_SCHEMA_NAME_ENTITY = ClientSchema.class.getSimpleName();
    public static final String CLIENT_SCHEMA_REQUEST_NAME_ENTITY = ClientSchema.class.getSimpleName();
    public static final String CLIENT_SCHEMA_RESPONSE_NAME_ENTITY = ClientSchema.class.getSimpleName();

    public static final String USER_NAME_ENTITY = UserSchema.class.getSimpleName();
    public static final String USER_RESPONSE_NAME_ENTITY = UserResponse.class.getSimpleName();
    public static final String USER_REQUEST_NAME_ENTITY = UserRequest.class.getSimpleName();

}