<?php
/**
 * The template part for displaying results in search pages
 *
 * @package WordPress
 * @subpackage HBA_Intranet_Theme
 * @since HBA Intranet Theme 1.0
 */
?>



<article class="news-item" id="post-<?php the_ID(); ?>" <?php post_class(); ?>>
	<header class="entry-header">
		<?php the_title( sprintf( '<h4 class="entry-title"><a href="%s" rel="bookmark">', esc_url( get_permalink() ) ), '</a></h4>' ); ?>
	</header><!-- .entry-header -->

	<?php hbaintranettheme_excerpt(); ?>

	<?php if ( 'post' === get_post_type() ) : ?>

		<!-- <?php hbaintranettheme_entry_meta(); ?> -->

	<?php endif; ?>
</article><!-- #post-## -->

