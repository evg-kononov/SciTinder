import {
    CHANGE_SEARCH_MENU
} from "../actions/types";

// const INITIAL_STATE = {
//     searchMenuIndex: null
// };

export default (state = {}, action) => {
    switch (action.type) {
        case CHANGE_SEARCH_MENU:
            return {...state, searchMenuIndex: action.payload};
        default:
            return state;
    }
};