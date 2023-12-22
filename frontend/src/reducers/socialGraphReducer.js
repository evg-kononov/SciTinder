import _ from "lodash";
import {
    CREATE_SOCIAL_GRAPH_FIGURE,
    CREATE_SOCIAL_GRAPH_FIGURE_ERROR,
    SOCIAL_GRAPH_FIGURE_ERROR_REMOVAL
} from "../actions/types";


export default (state = {}, action) => {
    switch (action.type) {
        case CREATE_SOCIAL_GRAPH_FIGURE:
            return {...state, [action.payload.menuIndex]: action.payload};
        case CREATE_SOCIAL_GRAPH_FIGURE_ERROR:
            return {...state, [action.payload.menuIndex]: action.payload};
        case SOCIAL_GRAPH_FIGURE_ERROR_REMOVAL:
            return _.omit(state, action.payload);
        default:
            return state;
    }
}