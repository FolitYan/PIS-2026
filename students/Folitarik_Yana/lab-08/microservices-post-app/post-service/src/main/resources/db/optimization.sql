CREATE INDEX idx_post_view_author ON post_details_view(authorName);

CREATE INDEX idx_post_view_updated ON post_details_view(lastUpdatedAt DESC);

CREATE INDEX idx_post_view_status ON post_details_view(status);
