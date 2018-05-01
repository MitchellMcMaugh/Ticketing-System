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
		<small>
			<?php echo __( '','eventorganiser' ) . ' '.eo_get_the_start( $format ); ?>
			<?php if( get_field('event_city') ): ?>
			 - <?php the_field('event_city'); ?>
			<?php endif; ?>
		</small>
		<?php the_title( sprintf( '<h4><a href="%s" rel="bookmark">', esc_url( get_permalink() ) ), '</a></h4>' ); ?>
	</header><!-- .entry-header -->

	<?php hbaintranettheme_excerpt(); ?>
</article><!-- #post-## -->
