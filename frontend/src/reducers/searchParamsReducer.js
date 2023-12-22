import {
    CHANGE_SEARCH_PARAMS
} from "../actions/types";

// const INITIAL_STATE = {
//     searchMenuIndex: null,
//     requestParams: {
//         page: 0,
//         size: 10
//     }
// };

export default (state = {}, action) => {
    switch (action.type) {
        case CHANGE_SEARCH_PARAMS:
            return {...state, [action.payload.searchMenuIndex]: action.payload};
        default:
            return state;
    }
}