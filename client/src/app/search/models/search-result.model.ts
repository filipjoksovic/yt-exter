import { SearchResultMinifiedModel } from "./search-result-minified.model";
import { Thumbnail } from "./thumbnail.model";

export type SearchResultModel = SearchResultMinifiedModel & {
  // thumbnail_url: string;
  thumbnail: { thumbnails: Array<Thumbnail> };
  lengthSeconds: number;
  viewCount: number;
  author: string;
  musicVideoType: string

}
