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
		<?php hbaintranettheme_entry_meta(); ?>
		<?php the_title( sprintf( '<h4><a href="%s" rel="bookmark">', esc_url( get_permalink() ) ), '</a></h4>' ); ?>
	</header><!-- .entry-header -->

	<?php if( get_field('excerpt') ): ?>
		<p><?php the_field('excerpt'); ?></p>
	<?php endif; ?>
</article><!-- #post-## -->
