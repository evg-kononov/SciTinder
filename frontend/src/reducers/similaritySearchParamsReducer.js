import {
    CHANGE_SIMILARITY_SEARCH_PARAMS
} from "../actions/types";

export default (state = {}, action) => {
    switch (action.type) {
        case CHANGE_SIMILARITY_SEARCH_PARAMS:
            return {...state, [action.menuIndex]: {...state[action.menuIndex], ...action.payload}};
        default:
            return state;
    }
}