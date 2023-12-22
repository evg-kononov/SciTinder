import _ from 'lodash';
import {
    FETCH_SCIENTISTS_BY_NAME_CONTAINING,
    FETCH_SCIENTISTS_BY_NAME_LIKE,
    FETCH_SCIENTISTS_BY_SCOPUS_ID,
    FETCH_SCIENTISTS_BY_NAME_LIKE_ERROR,
    FETCH_SCIENTISTS_BY_SCOPUS_ID_ERROR,
    FETCH_SCIENTISTS_BY_NAME_CONTAINING_ERROR,
    AUTHOR_SEARCH_ERROR_REMOVAL
} from "../actions/types";
import {menuItems} from "../components/sci_finder/constants";

// const INITIAL_STATE = {
//     authors: [],
//     totalItems: 0,
//     totalPages: 0,
//     currentPage: 0
// };

export default (state = {}, action) => {
    const {
        name,
        scopusId,
        fast
    } = menuItems;

    switch (action.type) {
        case FETCH_SCIENTISTS_BY_NAME_LIKE:
            return {...state, [name.index]: action.payload};
        case FETCH_SCIENTISTS_BY_SCOPUS_ID:
            return {...state, [scopusId.index]: action.payload};
        case FETCH_SCIENTISTS_BY_NAME_CONTAINING:
            return {...state, [fast.index]: action.payload};
        case FETCH_SCIENTISTS_BY_NAME_LIKE_ERROR:
            return {...state, [name.index]: action.payload};
        case FETCH_SCIENTISTS_BY_SCOPUS_ID_ERROR:
            return {...state, [scopusId.index]: action.payload};
        case FETCH_SCIENTISTS_BY_NAME_CONTAINING_ERROR:
            return {...state, [fast.index]: action.payload};
        case AUTHOR_SEARCH_ERROR_REMOVAL:
            return _.omit(state, action.payload);
        default:
            return state;
    }
};