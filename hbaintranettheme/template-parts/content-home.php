<?php
/**
 * The template part for displaying content
 *
 * @package WordPress
 * @subpackage HBA_Intranet_Theme
 * @since HBA Intranet Theme 1.0
 */
?>

<article class="news-item" id="post-<?php the_ID(); ?>" <?php post_class(); ?>>
	<header class="entry-header">
		<?php if ( is_sticky() ) : ?>
			<span class="sticky-post"><?php _e( 'Featured', 'hbaintranettheme' ); ?></span>
		<?php endif; ?>

		<?php hbaintranettheme_entry_meta(); ?>
		<span class="article-comments article-likes"><?php if(wp_ulike_get_post_likes(get_the_ID()) == '') { echo '0'; } else { echo wp_ulike_get_post_likes(get_the_ID()); }; ?></span>
		<span class="article-comments"><?php comments_number( '0', '1', '%' ); ?></span>
		<?php the_title( sprintf( '<h4><a href="%s" rel="bookmark">', esc_url( get_permalink() ) ), '</a></h4>' ); ?>
		

	</header><!-- .entry-header -->

	<?php if( get_field('excerpt') ): ?>
		<p><?php the_field('excerpt'); ?></p>
	<?php endif; ?>
	
	<?php if ( !is_sticky() ) : ?>
	<a class="read-more" href="<?php echo get_permalink(); ?>">Read more</a>
	<?php endif; ?>

	<!-- <a class="comments" href="<?php echo get_permalink(); ?>#respond"><?php comments_number( 'No comments', 'One comment', '% comments' ); ?></a> -->
	

</article><!-- #post-## -->
