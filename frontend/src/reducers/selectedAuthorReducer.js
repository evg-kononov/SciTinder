import {SELECT_AUTHOR, UNSELECT_AUTHOR} from "../actions/types";

// const INITIAL_STATE = {
//     isSelected: false,
//     authorId: null,
//     authorName: null
// };

export default (state = {}, action) => {
    switch (action.type) {
        case SELECT_AUTHOR:
            const {menuIndex, authorId, authorName} = action.payload;
            return {...state, [menuIndex]: {isSelected: true, authorId: authorId, authorName: authorName}};
        case UNSELECT_AUTHOR:
            return {...state, [action.payload]: {isSelected: false, authorId: null, authorName: null}};
        default:
            return state;
    }
};