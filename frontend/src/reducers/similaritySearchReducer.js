import _ from "lodash";
import {
    FETCH_SIMILAR_BY_ID,
    FETCH_SIMILAR_BY_ID_ERROR,
    SIMILARITY_SEARCH_ERROR_REMOVAL,
    FETCH_SIMILAR_BY_PROMPT,
    FETCH_SIMILAR_BY_PROMPT_ERROR
} from "../actions/types";


export default (state = {}, action) => {
    switch (action.type) {
        case FETCH_SIMILAR_BY_ID:
            return {...state, [action.payload.menuIndex]: action.payload};
        case FETCH_SIMILAR_BY_ID_ERROR:
            return {...state, [action.payload.menuIndex]: action.payload};
        case SIMILARITY_SEARCH_ERROR_REMOVAL:
            return _.omit(state, action.payload);
        case FETCH_SIMILAR_BY_PROMPT:
            return {...state, [action.payload.menuIndex]: action.payload};
        case FETCH_SIMILAR_BY_PROMPT_ERROR:
            return {...state, [action.payload.menuIndex]: action.payload};
        default:
            return state;
    }
}