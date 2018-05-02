<?php
class WP_Query {
	public $query;
	public $query_vars = array();
	public $tax_query;
	public $meta_query = false;
	public $date_query = false;
	public $queried_object;
	public $queried_object_id;
	public $request;
	public $posts;
	public $post_count = 0;
	public $current_post = -1;
	public $in_the_loop = false;
	public $post;
	public $comments;
	public $comment_count = 0;
	public $current_comment = -1;
	public $comment;
	public $found_posts = 0;
	public $max_num_pages = 0;
	public $max_num_comment_pages = 0;
	public $is_single = false;
	public $is_preview = false;
	public $is_page = false;
	public $is_archive = false;
	public $is_date = false;
	public $is_year = false;
	public $is_month = false;
	public $is_day = false;
	public $is_time = false;
	public $is_author = false;
	public $is_category = false;
	public $is_tag = false;
	public $is_tax = false;
	public $is_search = false;
	public $is_feed = false;
	public $is_comment_feed = false;
	public $is_trackback = false;
	public $is_home = false;
	public $is_404 = false;
	public $is_embed = false;
	public $is_paged = false;
	public $is_admin = false;
	public $is_attachment = false;
	public $is_singular = false;
	public $is_robots = false;
	public $is_posts_page = false;
	public $is_post_type_archive = false;
	private $query_vars_hash = false;
	private $query_vars_changed = true;
    public $thumbnails_cached = false;
	private $stopwords;
	private $compat_fields = array( 'query_vars_hash', 'query_vars_changed' );
	private $compat_methods = array( 'init_query_flags', 'parse_tax_query' );
	private function init_query_flags() {}
	public function init() {}
	public function parse_query_vars() {}
	public function fill_query_vars($array) {}
	public function parse_query( $query =  '' ) {}
	public function parse_tax_query( &$q ) {}
	protected function parse_search( &$q ) {}
	protected function parse_search_terms( $terms ) {}
	protected function get_search_stopwords() {}
	protected function parse_search_order( &$q ) {}
	protected function parse_orderby( $orderby ) {}
	protected function parse_order( $order ) {}
	public function set_404() {}
	public function get( $query_var, $default = '' ) {}
	public function set($query_var, $value) {}
	public function get_posts() {}
	private function set_found_posts( $q, $limits ) {}
	public function next_post() {}
	public function the_post() {}
	public function have_posts() {}
	public function rewind_posts() {}
	public function next_comment() {}
	public function the_comment() {}
	public function have_comments() {}
	public function rewind_comments() {}
	public function query( $query ) {}
	public function get_queried_object() {}
	public function get_queried_object_id() {}
	public function __construct( $query = '' ) {}
	public function __get( $name ) {}
	public function __isset( $name ) {}
	public function __call( $name, $arguments ) {}
	public function is_archive() {}
	public function is_post_type_archive( $post_types = '' ) {}
	public function is_attachment( $attachment = '' ) {}
	public function is_author( $author = '' ) {}
	public function is_category( $category = '' ) {}
	public function is_tag( $tag = '' ) {}
	public function is_tax( $taxonomy = '', $term = '' ) {}
	public function is_comments_popup() {}
	public function is_date() {}
	public function is_day() {}
	public function is_feed( $feeds = '' ) {}
	public function is_comment_feed() {}
	public function is_front_page() {}
	public function is_home() {}
	public function is_month() {}
	public function is_page( $page = '' ) {}
	public function is_paged() {}
	public function is_preview() {}
	public function is_robots() {}
	public function is_search() {}
	public function is_single( $post = '' ) {}
	public function is_singular( $post_types = '' ) {}
	public function is_time() {}
	public function is_trackback() {}
	public function is_year() {}
	public function is_404() {}
	public function is_embed() {}
	public function is_main_query() {}
	public function setup_postdata( $post ) {}
	public function reset_postdata() {}
	public function lazyload_term_meta( $check, $term_id ) {}
	public function lazyload_comment_meta( $check, $comment_id ) {}
}
