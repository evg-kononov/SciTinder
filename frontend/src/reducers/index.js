import {combineReducers} from "redux";
import {reducer as formReducer} from "redux-form";
import searchReducer from "./searchReducer";
import searchMenuReducer from "./searchMenuReducer";
import searchParamsReducer from "./searchParamsReducer";
import selectedAuthorReducer from "./selectedAuthorReducer";
import similaritySearchReducer from "./similaritySearchReducer";
import socialGraphReducer from "./socialGraphReducer";
import similaritySearchParamsReducer from "./similaritySearchParamsReducer";

export default combineReducers({
    searchMenu: searchMenuReducer,
    search: searchReducer,
    searchParams: searchParamsReducer,
    similaritySearchParams: similaritySearchParamsReducer,
    selectedAuthor: selectedAuthorReducer,
    similaritySearch: similaritySearchReducer,
    socialGraph: socialGraphReducer,
    form: formReducer
});